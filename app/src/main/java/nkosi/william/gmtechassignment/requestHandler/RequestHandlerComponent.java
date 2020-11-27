package nkosi.william.gmtechassignment.requestHandler;

import dagger.Component;

@Component
public interface RequestHandlerComponent {
    RequestHandler getRequestHandler();
}
