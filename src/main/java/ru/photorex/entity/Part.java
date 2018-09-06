package ru.photorex.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Заполните поле")
    @Column(name = "computer_part")
    private String computePart;

    @Column(name = "isneeded")
    private Character isNeeded;

    @Column(name = "quantity")
    private int quantity;

    public Part() {
    }

    public Part(String computePart, Character isNeeded, int quantity) {
        this.computePart = computePart;
        this.isNeeded = isNeeded;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputePart() {
        return computePart;
    }

    public void setComputePart(String computePart) {
        this.computePart = computePart;
    }

    public Character getIsNeeded() {
        return isNeeded;
    }

    public void setIsNeeded(Character isNeeded) {
        this.isNeeded = isNeeded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", computePart='" + computePart + '\'' +
                ", isNeeded=" + isNeeded +
                ", quantity=" + quantity +
                '}';
    }
}
