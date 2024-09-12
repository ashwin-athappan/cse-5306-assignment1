package org.as.service;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.as.proto.*;
import org.as.service.add.AddService;
import org.as.service.sort.SortService;

import java.util.Arrays;

@Slf4j
@GrpcService
@AllArgsConstructor
public class ServerService extends AddSortGrpc.AddSortImplBase {
    private final AddService addService;
    private final SortService sortService;

    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {
        try {
            int i = request.getI();
            int j = request.getJ();
            int sum = addService.add(i, j);
            AddResponse response = AddResponse.newBuilder().setSum(sum).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void sort(SortRequest request, StreamObserver<SortResponse> responseObserver) {
        try {
            Algorithm algorithm = request.getAlgorithm();
            int n = request.getArrayCount();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = request.getArray(i);
            }

            log.info("Sorting : {} using {}", array, algorithm);

            int[] sortedArray = new int[n];

            switch (algorithm) {
                case BUBBLE_SORT:
                    sortedArray = sortService.bubbleSort(array);
                    break;
                case INSERTION_SORT:
                    sortedArray = sortService.insertionSort(array);
                    break;
                case SELECTION_SORT:
                    sortedArray = sortService.selectionSort(array);
                    break;
                case MERGE_SORT:
                    sortedArray = sortService.mergeSort(array);
                    break;
                case QUICK_SORT:
                    sortedArray = Arrays.copyOf(array, n);
                    Arrays.sort(sortedArray);
                    break;
                default:
                    log.error("Unsupported algorithm: {}", algorithm);
            }

            log.info("Sorted : {} using {}", sortedArray, algorithm);

            if (sortedArray != null) {
                SortResponse.Builder builder = SortResponse.newBuilder();
                for (int i : sortedArray) {
                    builder.addArray(i);
                }
                SortResponse response = builder.setMessage("Sorted using " + algorithm).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        } catch (Exception e) {
            log.error(e.toString());
            Metadata metadata = new Metadata();
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException(metadata));
        }
    }
}
