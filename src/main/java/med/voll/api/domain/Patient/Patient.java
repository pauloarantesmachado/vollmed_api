package med.voll.api.domain.Patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "patient")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private Address address;

    private Boolean status;

    public Patient(DataRecordPatient data){
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.address = new Address(data.address());
        this.status = true;
    }

    public void updateData(DataUpdateRecordPatient data) {
        if(data.name()!= null){
            this.name = data.name();
        }
        if(data.phone()!= null){
            this.phone = data.phone();
        }
        if(data.address()!= null){
            this.address.updateDataAddress(data.address());
        }
    }

    public void delete() {
        this.status = false;
    }
}
