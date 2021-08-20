package com.qiwenshare.ufop.operation.delete.product;

import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qiwenshare.ufop.operation.delete.Deleter;
import com.qiwenshare.ufop.operation.delete.domain.DeleteFile;
import com.qiwenshare.ufop.util.UFOPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class FastDFSDeleter extends Deleter {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Override
    public void delete(DeleteFile deleteFile) {
        try {
            fastFileStorageClient.deleteFile(deleteFile.getFileUrl().replace("M00", "group1"));
        } catch (FdfsServerException e) {
            log.error(e.getMessage());
        }
        if (UFOPUtils.isImageFile(UFOPUtils.getFileExtendName(deleteFile.getFileUrl()))) {
            File cacheFile = UFOPUtils.getCacheFile(deleteFile.getFileUrl());
            if (cacheFile.exists()) {
                cacheFile.delete();
            }
        }
    }
}
