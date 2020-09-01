package service;

import domain.DubboServiceQuery;
import domain.DubboServiceResult;

public interface DubboService {

    DubboServiceResult invoke(DubboServiceQuery query);

}
