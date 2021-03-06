package org.ipc.synapsis.curriculumvitae.dao.impl;

import org.ipc.synapsis.curriculumvitae.dao.IAcademicBackgroundDao;
import org.ipc.synapsis.curriculumvitae.entity.AcademicBackground;
import org.ipc.synapsis.curriculumvitae.repository.IAcademicBackgroundRepository;
import org.ipc.synapsis.curriculumvitae.util.exception.ParseException;
import org.ipc.synapsis.curriculumvitae.util.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by mbasri on 09/07/2016.
 */
@Component
public class AcademicBackgroundDao implements IAcademicBackgroundDao {

    private static Logger LOGGER = LoggerFactory.getLogger(AcademicBackgroundDao.class);

    @Autowired
    IAcademicBackgroundRepository academicBackgroundRepository;

    @Override
    public AcademicBackground get(final String id) throws ParseException {
        LOGGER.debug("Start call Dao layer get a 'Academic Background',id:{}",id);
        AcademicBackground academicBackground = null;
        try{
            academicBackground = academicBackgroundRepository.findOne(UUID.fromString(id));
        }catch (IllegalArgumentException e){
            LOGGER.error("Dao layer Cannot parse String:{} to UUID",id);
            throw new ParseException(String.class.toString(),UUID.class.toString());
        }
        LOGGER.debug("End call Dao layer get a 'Academic Background',id:{}",id);
        return academicBackground;
    }

    @Override
    public Iterable<AcademicBackground> getAll() {
        LOGGER.debug("Start call Dao layer get all 'Academic Background'");
        Iterable<AcademicBackground> academicBackgroundsList = academicBackgroundRepository.findAll();
        LOGGER.debug("End call Dao layer get all 'Academic Background'");
        return  academicBackgroundsList;
    }

    @Override
    public AcademicBackground add(final AcademicBackground academicBackground) {
        LOGGER.debug("Start call Dao layer add new 'Academic Background',{}",academicBackground);
        AcademicBackground academicBackground1 = academicBackgroundRepository.save(academicBackground);
        LOGGER.debug("End call Dao layer add new 'Academic Background',{}",academicBackground1);
        return academicBackground1;
    }

    @Override
    public AcademicBackground update(final AcademicBackground academicBackground) {
        LOGGER.debug("End call Dao layer update 'Academic Background',{}",academicBackground);
        AcademicBackground academicBackground1 = academicBackgroundRepository.save(academicBackground);
        LOGGER.debug("End call Dao layer update 'Academic Background',{}",academicBackground1);
        return academicBackground1;
    }

    @Override
    public void remove(final String id) throws ParseException {
        LOGGER.debug("Start call Dao layer remove 'Academic Background',id:{}",id);
        try {
            academicBackgroundRepository.delete(UUID.fromString(id));
        }catch (IllegalArgumentException e){
            LOGGER.error("Dao layer Cannot parse String:{} to UUID",id);
            throw new ParseException(String.class.toString(),UUID.class.toString());
        }
        LOGGER.debug("End call Dao layer remove 'Academic Background',id:{}",id);
    }
}
