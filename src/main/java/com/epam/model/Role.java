package com.epam.model;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Role implements Serializable {

  public Role(String name) {
    this.name = name;
  }

  @Id
  private String id;

  @Indexed(unique = true)
  private String name;

}