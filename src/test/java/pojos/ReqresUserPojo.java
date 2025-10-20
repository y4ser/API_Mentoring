package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresUserPojo {

    private String name;
    private String job;

    public ReqresUserPojo() {
    }

    public ReqresUserPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ReqresUserPojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }


}
