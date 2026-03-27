package com.filehandlingsystem.fileHandling.service.serviceImpl;

import com.filehandlingsystem.fileHandling.config.FileStorageProperties;
import com.filehandlingsystem.fileHandling.exception.StorageException;
import com.filehandlingsystem.fileHandling.exception.StorageFileNotFoundException;
import com.filehandlingsystem.fileHandling.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation ;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties properties){

        if (properties.getLocation().trim().length() ==0 ){
            throw new StorageException("File upload location can not be empty");
        }

        this.rootLocation = Paths.get(properties.getLocation());

    }

    @Override
    public void init() {
       try{
           Files.createDirectories(rootLocation);
       } catch (IOException e) {
           throw new StorageException("Could not initialize storage",e);
       }
    }

    @Override
    public Boolean store(MultipartFile file,String id) {

        try{
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file");
            }

            File dir = new File(rootLocation.toUri());
            if(!dir.exists()) dir.mkdirs();

            String original = Paths.get(file.getOriginalFilename()).getFileName().toString();



            String destinationFile = original.replaceAll("[^a-zA-Z0-9.-]", "");

            String storedFilename = id+"_"+destinationFile;

            Path targetPath = Paths.get(String.valueOf(rootLocation), storedFilename);

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream,targetPath,StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file: "+e.getMessage());
        }

        return true;
    }

    @Override
    public Stream<Path> loadAll() {
        try{
            return Files
                    .walk(this.rootLocation,1)
                    .filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        }catch (IOException e){
            throw new StorageException("Failed to read files");
        }
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {
       try{
           Path file = load(fileName);
           Resource resource = new UrlResource(file.toUri());
           if(resource.exists() || resource.isReadable()){
               return resource;
           }else{
               throw new StorageFileNotFoundException("Could not read file: "+fileName);
           }
       } catch (MalformedURLException e) {
           throw new StorageFileNotFoundException("Could not read file :"+fileName);
       }
    }



    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
