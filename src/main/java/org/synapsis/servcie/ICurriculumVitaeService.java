package org.synapsis.servcie;

import org.synapsis.entity.CurriculumVitae;


/**
 * Created by mbasri on 19/06/2016.
 */
public interface ICurriculumVitaeService {
    CurriculumVitae get(String id);
    Iterable<CurriculumVitae> gelAll();
    void add(CurriculumVitae curriculumVitae);
    void update(CurriculumVitae curriculumVitae);
    void remove(String id);
}
