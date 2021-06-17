package com.qiwenshare.ufo.factory;

import com.qiwenshare.ufo.operation.delete.Deleter;
import com.qiwenshare.ufo.operation.delete.product.AliyunOSSDeleter;
import com.qiwenshare.ufo.operation.delete.product.FastDFSDeleter;
import com.qiwenshare.ufo.operation.delete.product.LocalStorageDeleter;
import com.qiwenshare.ufo.operation.download.Downloader;
import com.qiwenshare.ufo.operation.download.product.AliyunOSSDownloader;
import com.qiwenshare.ufo.operation.download.product.FastDFSDownloader;
import com.qiwenshare.ufo.operation.download.product.LocalStorageDownloader;
import com.qiwenshare.ufo.operation.preview.Previewer;
import com.qiwenshare.ufo.operation.preview.product.AliyunOSSPreviewer;
import com.qiwenshare.ufo.operation.preview.product.FastDFSPreviewer;
import com.qiwenshare.ufo.operation.preview.product.LocalStoragePreviewer;
import com.qiwenshare.ufo.operation.read.Reader;
import com.qiwenshare.ufo.operation.read.product.AliyunOSSReader;
import com.qiwenshare.ufo.operation.read.product.FastDFSReader;
import com.qiwenshare.ufo.operation.read.product.LocalStorageReader;
import com.qiwenshare.ufo.operation.rename.Renamer;
import com.qiwenshare.ufo.operation.rename.product.AliyunOSSRenamer;
import com.qiwenshare.ufo.operation.upload.Uploader;
import com.qiwenshare.ufo.operation.upload.product.AliyunOSSUploader;
import com.qiwenshare.ufo.operation.upload.product.FastDFSUploader;
import com.qiwenshare.ufo.operation.upload.product.LocalStorageUploader;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
public class UFOFactory {
    private String storageType;

    @Resource
    private FastDFSUploader fastDFSUploader;
    @Resource
    private FastDFSDownloader fastDFSDownloader;
    @Resource
    private  FastDFSDeleter fastDFSDeleter;
    @Resource
    private FastDFSReader fastDFSReader;
    @Resource
    private FastDFSPreviewer fastDFSPreviewer;

    public UFOFactory() {
    }

    public UFOFactory(String storageType) {
        this.storageType = storageType;
    }

    public Uploader getUploader() {

        int type = Integer.parseInt(storageType);

        if (StorageTypeEnum.LOCAL.getStorageType() == type) {
            return new LocalStorageUploader();
        } else if (StorageTypeEnum.ALIYUN_OSS.getStorageType() == type) {
            return new AliyunOSSUploader();
        } else if (StorageTypeEnum.FAST_DFS.getStorageType() == type) {
            return fastDFSUploader;
        }
        return null;
    }


    public Downloader getDownloader(int storageType) {
        if (StorageTypeEnum.LOCAL.getStorageType() == storageType) {
            return new LocalStorageDownloader();
        } else if (StorageTypeEnum.ALIYUN_OSS.getStorageType() == storageType) {
            return new AliyunOSSDownloader();
        } else if (StorageTypeEnum.FAST_DFS.getStorageType() == storageType) {
            return fastDFSDownloader;
        }
        return null;
    }


    public Deleter getDeleter(int storageType) {
        if (StorageTypeEnum.LOCAL.getStorageType() == storageType) {
            return new LocalStorageDeleter();
        } else if (StorageTypeEnum.ALIYUN_OSS.getStorageType() == storageType) {
            return new AliyunOSSDeleter();
        } else if (StorageTypeEnum.FAST_DFS.getStorageType() == storageType) {
            return fastDFSDeleter;
        }
        return null;
    }

    public Renamer getRenamer(int storageType) {
        if (StorageTypeEnum.LOCAL.getStorageType() == storageType) {
            return null;
        } else if (StorageTypeEnum.ALIYUN_OSS.getStorageType() == storageType) {
            return new AliyunOSSRenamer();
        } else if (StorageTypeEnum.FAST_DFS.getStorageType() == storageType) {
            return null;
        }
        return null;
    }

    public Reader getReader(int storageType) {
        if (StorageTypeEnum.LOCAL.getStorageType() == storageType) {
            return new LocalStorageReader();
        } else if (StorageTypeEnum.ALIYUN_OSS.getStorageType() == storageType) {
            return new AliyunOSSReader();
        } else if (StorageTypeEnum.FAST_DFS.getStorageType() == storageType) {
            return fastDFSReader;
        }
        return null;
    }

    public Previewer getPreviewer(int storageType) {
        if (StorageTypeEnum.LOCAL.getStorageType() == storageType) {
            return new LocalStoragePreviewer();
        } else if (StorageTypeEnum.ALIYUN_OSS.getStorageType() == storageType) {
            return new AliyunOSSPreviewer();
        } else if (StorageTypeEnum.FAST_DFS.getStorageType() == storageType) {
            return fastDFSPreviewer;
        }
        return null;
    }
}
