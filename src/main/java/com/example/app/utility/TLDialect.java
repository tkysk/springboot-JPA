package com.example.app.utility;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saeki on 2016/07/15.
 */
@Component
public class TLDialect extends AbstractDialect implements IExpressionEnhancingDialect {

    private static final Map<String, Object> EXPRESSION_OBJECTS;

    static {
        Map<String, Object> objects = new HashMap<>();
        objects.put("TLHelper", new TLUtility());
        EXPRESSION_OBJECTS = Collections.unmodifiableMap(objects);
    }

    public TLDialect() {
        super();
    }

    @Override
    public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
        return EXPRESSION_OBJECTS;
    }

    @Override
    public String getPrefix() {
        return null;
    }
}
