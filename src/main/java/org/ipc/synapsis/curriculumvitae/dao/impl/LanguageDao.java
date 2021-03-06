package org.ipc.synapsis.curriculumvitae.dao.impl;

import org.ipc.synapsis.curriculumvitae.dao.ILanguageDao;
import org.ipc.synapsis.curriculumvitae.entity.Language;
import org.ipc.synapsis.curriculumvitae.proxy.ILanguageProxy;
import org.ipc.synapsis.curriculumvitae.repository.ILanguageRepository;
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
public class LanguageDao implements ILanguageDao {

    private static Logger LOGGER = LoggerFactory.getLogger(LanguageDao.class);

    @Autowired
    ILanguageRepository languageRepository;

    @Override
    public Language get(final String id) throws ParseException {
        LOGGER.debug("Start call Dao layer get a 'Language',id:{}",id);
        Language language = null;
        try {
            language = languageRepository.findOne(UUID.fromString(id));
        }catch (IllegalArgumentException e){
            LOGGER.error("Dao layer Cannot parse String:{} to UUID",id);
            throw new ParseException(String.class.toString(),UUID.class.toString());
        }
        LOGGER.debug("End call Dao layer get a 'Language',id:{}",id);
        return language;
    }

    @Override
    public Iterable<Language> getAll() {
        LOGGER.debug("Start call Dao layer get all 'Language'");
        Iterable<Language> languageList = languageRepository.findAll();
        LOGGER.debug("End call Dao layer get all 'Language'");
        return languageList;
    }

    @Override
    public Language add(final Language language) {
        LOGGER.debug("Start call Dao layer add new 'Language',{}",language);
        Language language1 = languageRepository.save(language);
        LOGGER.debug("End call Dao layer add new 'Language',{}",language1);
        return language1;
    }

    @Override
    public Language update(final Language language) {
        LOGGER.debug("End call Dao layer update 'Language',{}",language);
        Language language1 = languageRepository.save(language);
        LOGGER.debug("End call Dao layer update 'Language',{}",language1);
        return language1;
    }

    @Override
    public void remove(final String id) throws ParseException {
        LOGGER.debug("Start call Dao layer remove 'Language',id:{}",id);
        try {
        languageRepository.delete(UUID.fromString(id));
        }catch (IllegalArgumentException e){
            LOGGER.error("Dao layer Cannot parse String:{} to UUID",id);
            throw new ParseException(String.class.toString(),UUID.class.toString());
        }
        LOGGER.debug("End call Dao layer remove 'Language',id:{}",id);
    }
}
