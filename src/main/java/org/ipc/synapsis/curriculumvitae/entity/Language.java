package org.ipc.synapsis.curriculumvitae.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.util.UUID;

/**
 * Created by mbasri on 02/07/2016.
 */
@Entity
@ApiModel(value = "'Academic Background'", description = "The 'Academic Background' model")
public class Language {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @ApiModelProperty(value = "'Curriculum Vitae' id property", dataType = "java.util.UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_CURRICULUM_VITAE",nullable = false)
    @ApiModelProperty(value = "Reference to 'Curriculum Vitae'", dataType = "org.ipc.synapsis.curriculumvitae.entity.CurriculumVitae")
    private CurriculumVitae curriculumVitae;
}