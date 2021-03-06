package net.cb.dcm.jpa.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "playlists")
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=250)
	private String description;
	
	@Column
	private int priority;
	
	@Column
	private boolean active;
	
	@Column
	private boolean def;
	
	@Column(name="VALID_FROM")
	@Temporal(TemporalType.DATE)
	private Date validFrom;
	
	@Column(name="VALID_TO")
	@Temporal(TemporalType.DATE)
	private Date validTo;
	
	@OneToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinTable(name = "playlist_media_contents", 
	joinColumns = @JoinColumn(name = "PLAYLIST_ID"), 
	inverseJoinColumns = @JoinColumn(name = "MEDIA_CONTENT_ID"))
	private List<MediaContent> mediaContents;
	
//	@OneToMany(mappedBy="playlist", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST},
//			orphanRemoval = true)
	@OneToMany(mappedBy="playlist", cascade = {CascadeType.REMOVE})
	private List<PlaylistSchedule> schedules;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public List<MediaContent> getMediaContents() {
		return mediaContents;
	}

	public void setMediaContents(List<MediaContent> mediaContents) {
		this.mediaContents = mediaContents;
	}

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	public List<PlaylistSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<PlaylistSchedule> schedules) {
		this.schedules = schedules;
	} 
	
}
