package com.shxy.model;

import java.io.Serializable;

public class Ztree implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column z_tree.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column z_tree.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column z_tree.pid
     *
     * @mbggenerated
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column z_tree.sortvalue
     *
     * @mbggenerated
     */
    private String sortvalue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column z_tree.file
     *
     * @mbggenerated
     */
    private String file;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column z_tree.open
     *
     * @mbggenerated
     */
    private Byte open;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table z_tree
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column z_tree.id
     *
     * @return the value of z_tree.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column z_tree.id
     *
     * @param id the value for z_tree.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column z_tree.name
     *
     * @return the value of z_tree.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column z_tree.name
     *
     * @param name the value for z_tree.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column z_tree.pid
     *
     * @return the value of z_tree.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column z_tree.pid
     *
     * @param pid the value for z_tree.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column z_tree.sortvalue
     *
     * @return the value of z_tree.sortvalue
     *
     * @mbggenerated
     */
    public String getSortvalue() {
        return sortvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column z_tree.sortvalue
     *
     * @param sortvalue the value for z_tree.sortvalue
     *
     * @mbggenerated
     */
    public void setSortvalue(String sortvalue) {
        this.sortvalue = sortvalue == null ? null : sortvalue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column z_tree.file
     *
     * @return the value of z_tree.file
     *
     * @mbggenerated
     */
    public String getFile() {
        return file;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column z_tree.file
     *
     * @param file the value for z_tree.file
     *
     * @mbggenerated
     */
    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column z_tree.open
     *
     * @return the value of z_tree.open
     *
     * @mbggenerated
     */
    public Byte getOpen() {
        return open;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column z_tree.open
     *
     * @param open the value for z_tree.open
     *
     * @mbggenerated
     */
    public void setOpen(Byte open) {
        this.open = open;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table z_tree
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Ztree other = (Ztree) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getSortvalue() == null ? other.getSortvalue() == null : this.getSortvalue().equals(other.getSortvalue()))
            && (this.getFile() == null ? other.getFile() == null : this.getFile().equals(other.getFile()))
            && (this.getOpen() == null ? other.getOpen() == null : this.getOpen().equals(other.getOpen()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table z_tree
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getSortvalue() == null) ? 0 : getSortvalue().hashCode());
        result = prime * result + ((getFile() == null) ? 0 : getFile().hashCode());
        result = prime * result + ((getOpen() == null) ? 0 : getOpen().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table z_tree
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", sortvalue=").append(sortvalue);
        sb.append(", file=").append(file);
        sb.append(", open=").append(open);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}