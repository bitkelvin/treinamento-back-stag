package br.com.seniorsolution.estagiario.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import br.com.seniorsolution.estagiario.services.MessageService;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl implements MessageService {

    @Autowired
    MessageSource messages;

    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, Object... attrs) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, attrs, locale);
    }

    @Override
    public String getMessage(String key, Locale locale) {
        return messages.getMessage(key, null, locale);
    }
    
}