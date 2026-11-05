package automated_testing_api_senior.requests.skelethon.interfaces;

import automated_testing_api_senior.models.BaseModel;

public interface CrudEndpointInterface {

    Object post(BaseModel model);

    Object get(long id);
}
