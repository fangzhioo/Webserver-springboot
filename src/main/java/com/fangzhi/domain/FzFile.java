package com.fangzhi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fz_files")
public class FzFile{

    @Id
	@Column(name = "file_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "file_name")
	private String name;
	
    @Column(name = "file_type")
	private String type;
	
    @Column(name = "file_created")
	private Long created;
	
    @Column(name = "file_key")
	private String key;
	
    @Column(name = "file_authorid")
    private Long authorid;

	@Column(name = "file_dir")
	private String dir;
	
    public FzFile(){}

    public FzFile(String filename, String filekey, String filetype ,Long authorid) {
        this.name = filename;
        this.type = filetype;
        this.key = filekey;
        this.authorid = authorid;
    }
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the created
	 */
	public Long getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Long created) {
		this.created = created;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the authorid
	 */
	public Long getAuthorid() {
		return authorid;
	}
	/**
	 * @param authorid the authorid to set
	 */
	public void setAuthorid(Long authorid) {
		this.authorid = authorid;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	

}