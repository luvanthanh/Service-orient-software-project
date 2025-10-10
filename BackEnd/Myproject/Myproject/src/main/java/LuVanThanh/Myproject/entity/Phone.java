package LuVanThanh.Myproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "phones")
public class Phone {

    @Id
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "productName")
    private String productName;

    @Column(name = "screenSize")
    private Double screenSize;

    @Column(name = "color")
    private String color;

    @Column(name = "ram")
    private int ram;

    @Column(name = "rom")
    private int rom;

    @Column(name = "description")
    private String description;

    @Column(name = "releaseDate")
    private LocalDate releaseDate;

    @Column(name = "stockQuantity")
    private int stockQuantity;

    @Column(name = "warranty")
    private int warranty;

    @Column(name = "price")
    private long price;

    @Column(name = "formattedPrice")
    private String formattedPrice;

    @Column(name = "imageUrl")
    private String imageUrl;
}

