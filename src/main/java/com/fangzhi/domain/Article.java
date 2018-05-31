package com.fangzhi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fz_articles")
public class Article{

    @Id
	@Column(name = "article_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Column(name = "article_title")
    private String title; // 标题
    
    @Column(name = "article_slug")
    private String slug; // 文章缩略名

    @Column(name = "article_created")
    private Integer created; // 创建时间
    
    @Column(name = "article_modified")
    private Integer modified; // 最后修改时间

    @Column(name = "article_author_id")
    private Integer authorId;

    @Column(name = "article_type")
    private String type;

    @Column(name = "article_status")
    private String status;

    @Column(name = "article_tags")
    private String tags;

    @Column(name = "article_categories")
    private String categories;

    @Column(name = "article_hits")
    private Integer hits;

    @Column(name = "article_comments_num")
    private Integer commentsNum;

    @Column(name = "article_allow_comment")
    private Boolean allowComment;

    @Column(name = "article_allow_ping")
    private Boolean allowPing;

    @Column(name = "article_allow_feed")
    private Boolean allowFeed;

    @Column(name = "article_content")
    private String content;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}
	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}
	/**
	 * @return the created
	 */
	public Integer getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Integer created) {
		this.created = created;
	}
	/**
	 * @return the modified
	 */
	public Integer getModified() {
		return modified;
	}
	/**
	 * @param modified the modified to set
	 */
	public void setModified(Integer modified) {
		this.modified = modified;
	}
	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * @return the categories
	 */
	public String getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String categories) {
		this.categories = categories;
	}
	/**
	 * @return the hits
	 */
	public Integer getHits() {
		return hits;
	}
	/**
	 * @param hits the hits to set
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	/**
	 * @return the commentsNum
	 */
	public Integer getCommentsNum() {
		return commentsNum;
	}
	/**
	 * @param commentsNum the commentsNum to set
	 */
	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}
	/**
	 * @return the allowPing
	 */
	public Boolean getAllowPing() {
		return allowPing;
	}
	/**
	 * @param allowPing the allowPing to set
	 */
	public void setAllowPing(Boolean allowPing) {
		this.allowPing = allowPing;
	}
	/**
	 * @return the allowComment
	 */
	public Boolean getAllowComment() {
		return allowComment;
	}
	/**
	 * @param allowComment the allowComment to set
	 */
	public void setAllowComment(Boolean allowComment) {
		this.allowComment = allowComment;
	}
	/**
	 * @return the allowFeed
	 */
	public Boolean getAllowFeed() {
		return allowFeed;
	}
	/**
	 * @param allowFeed the allowFeed to set
	 */
	public void setAllowFeed(Boolean allowFeed) {
		this.allowFeed = allowFeed;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
    
    
}