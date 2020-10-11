package ro.sda.spring.boot.dto;

import java.util.List;

public class PageableDoctorResponseDTO {

    private Integer size;
    private Integer page;
    private Long total;
    private List<DoctorDTO> doctors;

    public PageableDoctorResponseDTO(Integer size, Integer page, Long total, List<DoctorDTO> doctors) {
        this.size = size;
        this.page = page;
        this.total = total;
        this.doctors = doctors;
    }

    public PageableDoctorResponseDTO() {
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorDTO> doctors) {
        this.doctors = doctors;
    }
}
