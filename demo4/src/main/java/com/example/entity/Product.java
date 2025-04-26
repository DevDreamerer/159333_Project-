package com.example.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2025-04-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Product implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String description;

    private Float price;

    private Integer stock;

    private Integer categoryleveloneId;

    private Integer categoryleveltwoId;

    private Integer categorylevelthreeId;

    private String fileName;

    private String origin;

    private LocalDateTime shelfTime;

    private String brand;

    private BigDecimal rating;

  public Product() {
  }

  public Product(Integer id, String name, String description, Float price, Integer stock, Integer categoryleveloneId, Integer categoryleveltwoId, Integer categorylevelthreeId, String fileName, String origin, LocalDateTime shelfTime, String brand, BigDecimal rating) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.categoryleveloneId = categoryleveloneId;
    this.categoryleveltwoId = categoryleveltwoId;
    this.categorylevelthreeId = categorylevelthreeId;
    this.fileName = fileName;
    this.origin = origin;
    this.shelfTime = shelfTime;
    this.brand = brand;
    this.rating = rating;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Integer getCategoryleveloneId() {
    return categoryleveloneId;
  }

  public void setCategoryleveloneId(Integer categoryleveloneId) {
    this.categoryleveloneId = categoryleveloneId;
  }

  public Integer getCategoryleveltwoId() {
    return categoryleveltwoId;
  }

  public void setCategoryleveltwoId(Integer categoryleveltwoId) {
    this.categoryleveltwoId = categoryleveltwoId;
  }

  public Integer getCategorylevelthreeId() {
    return categorylevelthreeId;
  }

  public void setCategorylevelthreeId(Integer categorylevelthreeId) {
    this.categorylevelthreeId = categorylevelthreeId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public LocalDateTime getShelfTime() {
    return shelfTime;
  }

  public void setShelfTime(LocalDateTime shelfTime) {
    this.shelfTime = shelfTime;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }
}
