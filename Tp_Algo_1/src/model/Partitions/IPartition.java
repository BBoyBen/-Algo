package model.Partitions;

import java.util.List;

public interface IPartition {
    Partition getPartitionByNum(int num);
    void calculPartitions();
    List<Partition> getPartitions();
    void afficherPartition();
}
