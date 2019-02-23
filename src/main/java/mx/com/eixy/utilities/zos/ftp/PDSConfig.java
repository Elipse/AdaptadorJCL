package mx.com.eixy.utilities.zos.ftp;

public class PDSConfig {

	private String primarySpace;
	private String secondarySpace;
	private String spaceUnit;
	private String recordFormat;
	private String recordLength;
	private String blkSize;
	private String directorySize;

	public String getPrimarySpace() {
		return primarySpace;
	}

	public PDSConfig setPrimarySpace(String primarySpace) {
		this.primarySpace = primarySpace;
		return this;
	}

	public String getSecondarySpace() {
		return secondarySpace;
	}

	public PDSConfig setSecondarySpace(String secondarySpace) {
		this.secondarySpace = secondarySpace;
		return this;
	}

	public String getSpaceUnit() {
		return spaceUnit;
	}

	public PDSConfig setSpaceUnit(String spaceUnit) {
		this.spaceUnit = spaceUnit;
		return this;
	}

	public String getRecordFormat() {
		return recordFormat;
	}

	public PDSConfig setRecordFormat(String recordFormat) {
		this.recordFormat = recordFormat;
		return this;
	}

	public String getRecordLength() {
		return recordLength;
	}

	public PDSConfig setRecordLength(String recordLength) {
		this.recordLength = recordLength;
		return this;
	}

	public String getBlkSize() {
		return blkSize;
	}

	public PDSConfig setBlkSize(String blkSize) {
		this.blkSize = blkSize;
		return this;
	}

	public String getDirectorySize() {
		return directorySize;
	}

	public PDSConfig setDirectorySize(String directorySize) {
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
