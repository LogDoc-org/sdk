package ru.gang.logdoc.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Denis Danilin | me@loslobos.ru
 * 21.12.2021 11:55
 * sdk ☭ sweat and blood
 */
public abstract class ParametrizedPipePlugin implements PipePlugin {
    protected final Map<String, Object> parametersValues;
    protected PipePluginManager manager;

    protected ParametrizedPipePlugin() {
        parametersValues = new HashMap<>();
    }

    public abstract List<PluginParameter<?>> declareParameters();

    @Override
    public void setManager(final PipePluginManager manager) {
        this.manager = manager;
    }

    public void setParameters(final Map<String, ?> parameters) throws RequiredParameterValueMissed {
        final List<PluginParameter<?>> declared = declareParameters();

        if (declared == null || declared.isEmpty())
            return;

        for (final PluginParameter<?> p : declared) {
            if (p.isRequired() && (!parameters.containsKey(p.name()) || parameters.get(p.name()) == null))
                throw new RequiredParameterValueMissed(p.name());

            final Object v = parameters.get(p.name());

            if (v != null)
                parametersValues.put(p.name(), v);
        }
    }
}
