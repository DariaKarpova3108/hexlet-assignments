package exercise.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


// id – уникальный идентификатор id, генерируется автоматически базой данных
//title — название задачи
//description – описание задачи
//createdAt – дата создания новой задачи
//updatedAt – дата последнего обновления задачи


// BEGIN
@Entity
@Table(name = "tasks")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"title", "description"})
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String title;
    private String description;
    @CreatedDate
    private LocalDate createdAt;
    @LastModifiedDate
    private LocalDate updatedAt;

}
// END
