package ca.bazlur.llmspring;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyModelChatListener implements ChatModelListener {

    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        log.info("onRequest() called with: requestContext = [{}]", requestContext);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        log.info("onResponse() called with: responseContext = [{}]", responseContext);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("onError() called with: errorContext = [{}]", errorContext);
    }
}
