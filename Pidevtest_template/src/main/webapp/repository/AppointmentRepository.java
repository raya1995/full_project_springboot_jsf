package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Client;

public interface AppointmentRepository extends CrudRepository<Appointment,Long>{
	@Query("SELECT a FROM Appointment a WHERE a.state=:confirmed ")
	List<Appointment> retrieveAllAppointmentByState(@Param("confirmed")String State);
	@Query("SELECT a FROM Appointment a WHERE a.purchased=:false  ")
	List<Appointment> retrieveAllAppointmentBypurchasing(@Param("false")boolean purchased);
	@Query("SELECT c FROM Appointment c Where c.Attendance=:absent")
	List<Appointment> retrieveAllAttendanceByClient(@Param("absent")String Attendance);
	@Query("SELECT c FROM Appointment c Where c.DatePurchasing is NULL")
	List<Appointment> AppointmentByDatePurchasingNull(@Param("NULL")Date DatePurchasing);
	@Query("select DISTINCT a from Appointment a join a.client u where a.client=:ClientConnecte")
	List<Appointment> MyAppointment(@Param("ClientConnecte")Client client);
	@Query("select DISTINCT a from Appointment a join a.ad d join d.user where d.user=:ClientConnecte")
	List<Appointment> ReceivedAppointment(@Param("ClientConnecte")Client client);
	 @Query("SELECT a FROM Appointment a WHERE a.idAppointement=(SELECT MAX(a.idAppointement)FROM Appointment) ")
	 List<Appointment> lastAppointmentAdded();
	
	

}
