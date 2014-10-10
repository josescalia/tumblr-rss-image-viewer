package com.josescalia.tumblr.util.model;

import java.io.File;
import java.util.List;

/**
 * Created by josescalia on 13/07/14.
 */
public class CacheFile {
    private String folderName;
    private int fileListSize;
    private String totalFolderSize;
    private List<File> fileList;

    public CacheFile() {
    }

    public CacheFile(String folderName, int fileListSize, String totalFolderSize, List<File> fileList) {
        this.folderName = folderName;
        this.fileListSize = fileListSize;
        this.totalFolderSize = totalFolderSize;
        this.fileList = fileList;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getFileListSize() {
        return fileListSize;
    }

    public void setFileListSize(int fileListSize) {
        this.fileListSize = fileListSize;
    }

    public String getTotalFolderSize() {
        return totalFolderSize;
    }

    public void setTotalFolderSize(String totalFolderSize) {
        this.totalFolderSize = totalFolderSize;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    @Override
    public String toString() {
        return "CacheFile{" +
                "folderName='" + folderName + '\'' +
                ", fileListSize=" + fileListSize +
                ", totalFolderSize='" + totalFolderSize + '\'' +
                ", fileList=" + fileList.size() +
                '}';
    }
}
