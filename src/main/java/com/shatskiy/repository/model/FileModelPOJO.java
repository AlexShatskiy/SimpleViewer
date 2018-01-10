package com.shatskiy.repository.model;

import java.math.BigInteger;

/**
 * It is model file or folder
 * @author Shatskiy Alex
 * @version 1.0
 */
public class FileModelPOJO {

    private String fileName;
    private String fullPath;
    private Boolean isDirectory;
    private BigInteger size;
    private String parentFileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Boolean getDirectory() {
        return isDirectory;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public String getParentFileName() {
        return parentFileName;
    }

    public void setParentFileName(String parentFileName) {
        this.parentFileName = parentFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileModelPOJO that = (FileModelPOJO) o;

        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (fullPath != null ? !fullPath.equals(that.fullPath) : that.fullPath != null) return false;
        if (isDirectory != null ? !isDirectory.equals(that.isDirectory) : that.isDirectory != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        return parentFileName != null ? parentFileName.equals(that.parentFileName) : that.parentFileName == null;
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (fullPath != null ? fullPath.hashCode() : 0);
        result = 31 * result + (isDirectory != null ? isDirectory.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (parentFileName != null ? parentFileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileModelPOJO{" +
                "fileName='" + fileName + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", isDirectory=" + isDirectory +
                ", size=" + size +
                ", parentFileName='" + parentFileName + '\'' +
                '}';
    }
}
