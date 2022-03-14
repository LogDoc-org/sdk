package ru.gang.logdoc.sdk.entrypipes;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import play.libs.Json;
import ru.gang.logdoc.sdk.PipePlugin;
import ru.gang.logdoc.sdk.WatcherMetrics;
import ru.gang.logdoc.structs.LogEntry;

import java.util.Map;

import static ru.gang.logdoc.utils.Tools.getBoolean;

public class WsNotifier implements PipePlugin {
    private static final String FULL_NAME = "wsFullReport";
    private static final JsonNode finita = Json.parse("{\"message\": \"Listener is interrupted, no data will be delivered\"}");
    protected ActorRef wsLink;

    @Override
    public void configure(final Config config) {}

    @Override
    public void watcherActuated(final String watcherId, final LogEntry entry, final WatcherMetrics metrics, final Map<String, ?> ctx) {
        if (wsLink != null)
            wsLink.tell(Json.toJson(getBoolean(ctx.get(FULL_NAME)) ? metrics : entry), ActorRef.noSender());
    }

    public final void setWsLink(final ActorRef wsLink) {
        this.wsLink = wsLink;
    }

    @Override
    public void watcherStopped(final String watcherId) {
        if (wsLink == null)
            return;

        wsLink.tell(finita, ActorRef.noSender());
        wsLink.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
