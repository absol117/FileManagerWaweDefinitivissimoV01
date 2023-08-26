package com.scai.filemanager_wave2_v02.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String path;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @Transient
    private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User User_Id;

}
