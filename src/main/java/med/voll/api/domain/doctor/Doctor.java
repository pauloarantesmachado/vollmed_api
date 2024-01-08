package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "doctor")
@Entity(name = "doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private EnumMedicalSpecialty medicalSpecialty;
    @Embedded
    private Address address;

    private Boolean status;

    public Doctor(DataRecordDoctor data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.medicalSpecialty = data.medicalSpecialty();
        this.address = new Address(data.address());
        this.status = true;
    }

    public void updateData(DataUpdateRecordDoctor data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.phone() !=null){
            this.phone = data.phone();
        }
        if(data.address() != null){
            this.address.updateDataAddress(data.address());
        }

    }

    public void delete() {
        this.status = false;
    }
}
