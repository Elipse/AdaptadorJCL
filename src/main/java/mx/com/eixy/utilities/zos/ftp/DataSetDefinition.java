/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.eixy.utilities.zos.ftp;

/**
 *
 * @author ELIALVA
 */
public class DataSetDefinition {

    private String dsname;
    private String primarySpace;
    private String secondarySpace;
    private String spaceUnit;
    private String recordFormat;
    private String recordLength;
    private String blkSize;
    private String directorySize;

    public static DataSetDefinition newDataDefinition() {
        return new DataSetDefinition();
    }

    public String getDsname() {
        return dsname;
    }

    public DataSetDefinition setDsname(String dsname) {
        this.dsname = dsname;
        return this;
    }

    public String getPrimarySpace() {
        return primarySpace;
    }

    public DataSetDefinition setPrimarySpace(String primarySpace) {
        this.primarySpace = primarySpace;
        return this;
    }

    public String getSecondarySpace() {
        return secondarySpace;
    }

    public DataSetDefinition setSecondarySpace(String secondarySpace) {
        this.secondarySpace = secondarySpace;
        return this;
    }

    public String getSpaceUnit() {
        return spaceUnit;
    }

    public DataSetDefinition setSpaceUnit(String spaceUnit) {
        this.spaceUnit = spaceUnit;
        return this;
    }

    public String getRecordFormat() {
        return recordFormat;
    }

    public DataSetDefinition setRecordFormat(String recordFormat) {
        this.recordFormat = recordFormat;
        return this;
    }

    public String getRecordLength() {
        return recordLength;
    }

    public DataSetDefinition setRecordLength(String recordLength) {
        this.recordLength = recordLength;
        return this;
    }

    public String getBlkSize() {
        return blkSize;
    }

    public DataSetDefinition setBlkSize(String blkSize) {
        this.blkSize = blkSize;
        return this;
    }

    public String getDirectorySize() {
        return directorySize;
    }

    public DataSetDefinition setDirectorySize(String directorySize) {
        this.directorySize = directorySize;
        return this;
    }

    @Override
    public String toString() {
        String toString = primarySpace + " " + secondarySpace + " " + spaceUnit + " " + recordFormat + " "
                + recordLength + " " + blkSize + " " + directorySize;
        return toString;
    }

}
