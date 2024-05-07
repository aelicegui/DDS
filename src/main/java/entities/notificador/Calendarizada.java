package entities.notificador;

import javax.persistence.*;
import java.util.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@DiscriminatorValue("Calendarizada")
public class Calendarizada extends TipoNotificacion{
    @Column(name="horaNotificacion", columnDefinition = "TIME")
    private LocalTime hora;

    @ManyToMany
    @JoinTable(name = "notificacion_calendarizada",
            joinColumns = @JoinColumn(name = "calendarizada_id"), inverseJoinColumns = @JoinColumn(name = "notificacion_id"))
    private List<Notificacion> notificaciones;

    public Calendarizada(LocalTime hora){
        this.hora = hora;
        this.notificaciones = new ArrayList<>();
    }

    public Calendarizada() {
        this.notificaciones = new ArrayList<>();
    }


    @Override
    public void notificar(Notificacion notificacion) {
        if (this.notificaciones.isEmpty()){
            programar();
        }
        this.agregarNotificacion(notificacion);
    }

    private void programar() {
        Timer t = new Timer();
        Date date = new Date();
       // Date dateNow = new Date();
        date.setHours(hora.getHour());
        date.setMinutes(hora.getMinute());
        date.setSeconds(hora.getSecond());
      /*  if(date.after(dateNow)){
            LocalDateTime maniana = LocalDateTime.now().plusDays(1);
            date.setDate(maniana.getDayOfMonth());
            date.setMonth(maniana.getMonthValue());
            date.setYear(maniana.getYear());
        }*/
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                notificarIncidentes();
            };
        };
        t.schedule(tt, date);
    }


    public void agregarNotificacion(Notificacion... notificacionesNuevas) {
        Collections.addAll(this.notificaciones, notificacionesNuevas);
    }

    public void notificarIncidentes(){
        this.notificaciones.forEach(notificacion ->
                notificacion.getMedioNotificacion().notificar(notificacion.getIncidente(), notificacion.getUsuario()));
        this.notificaciones.clear();

    }

}

