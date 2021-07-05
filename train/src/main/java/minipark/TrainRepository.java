package minipark;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="trains", path="trains")
public interface TrainRepository extends PagingAndSortingRepository<Train, Long>{

    Train findByTrainId(Long trainId);


}
