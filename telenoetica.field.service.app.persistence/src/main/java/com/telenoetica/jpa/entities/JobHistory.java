package com.telenoetica.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;


@Entity
@Table(name = "job_history")
@JsonAutoDetect(JsonMethod.NONE)
public class JobHistory implements BaseEntity,Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = -3083249622983667890L;

  @JsonProperty
  private Long id;
  
  private Integer version;
  
  @JsonProperty
  private String jobName;
  
  @JsonProperty
  private String description;
  
  @JsonProperty
  private Date startTime;
  
  @JsonProperty
  private Date endTime;
  
  @JsonProperty
  private JobStatus jobStatus;

  public JobHistory() {
    super();
    // TODO Auto-generated constructor stub
  }

  public JobHistory(String jobName, String description, Date startTime, Date endTime,
      JobStatus jobStatus) {
    super();
    this.jobName = jobName;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
    this.jobStatus = jobStatus;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "job_name", length = 250)
  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  @Column(name = "description", length = 250)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_time", nullable = false, length = 19)
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end_time", nullable = false, length = 19)
  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  public JobStatus getJobStatus() {
    return jobStatus;
  }

  public void setJobStatus(JobStatus jobStatus) {
    this.jobStatus = jobStatus;
  }
  
  @Version
  @Column(name = "version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("JobHistory [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
    if (version != null) {
      builder.append("version=");
      builder.append(version);
      builder.append(", ");
    }
    if (jobName != null) {
      builder.append("jobName=");
      builder.append(jobName);
      builder.append(", ");
    }
    if (description != null) {
      builder.append("description=");
      builder.append(description);
      builder.append(", ");
    }
    if (startTime != null) {
      builder.append("startTime=");
      builder.append(startTime);
      builder.append(", ");
    }
    if (endTime != null) {
      builder.append("endTime=");
      builder.append(endTime);
      builder.append(", ");
    }
    if (jobStatus != null) {
      builder.append("jobStatus=");
      builder.append(jobStatus);
    }
    builder.append("]");
    return builder.toString();
  }

  
  
  

}
