package org.as.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.as.proto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService {
    private ConsoleService consoleService;
    private ManagedChannel channel;
    private AddSortGrpc.AddSortStub asyncStub;
    private AddSortGrpc.AddSortBlockingStub syncStub;

    public ClientService() {
        consoleService = new ConsoleService();
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        asyncStub = AddSortGrpc.newStub(channel);
        syncStub = AddSortGrpc.newBlockingStub(channel);
    }

    public int add(int i, int j) {
        AddRequest request = AddRequest.newBuilder().setI(i).setJ(j).build();
        AddResponse response = syncStub.add(request);
        int sum = response.getSum();
        return sum;
    }

    public List<Integer> sort(Algorithm algorithm, List<Integer> array) {
        SortRequest.Builder builder = SortRequest.newBuilder();
        for (Integer i : array) {
            builder.addArray(i);
        }
        builder.setAlgorithm(algorithm);
        SortRequest request = builder.build();
        SortResponse response = syncStub.sort(request);
        List<Integer> sortedArray = new ArrayList<>();
        int n = response.getArrayCount();
        for (int i = 0; i < n; i++) {
            sortedArray.add(response.getArray(i));
        }
        return sortedArray;
    }

    public void add(int i, int j, CompletableFuture<String> future) {
        AddRequest request = AddRequest.newBuilder().setI(i).setJ(j).build();
        asyncStub.add(request, new StreamObserver<AddResponse>() {
            @Override
            public void onNext(AddResponse addResponse) {
                int sum = addResponse.getSum();
                future.complete("" + sum);
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                future.complete("Error : " + s.getDescription());
            }

            @Override
            public void onCompleted() {

            }
        });
        consoleService.write("Acknowledgement Received");
    }

    public void sort(Algorithm algorithm, List<Integer> array, CompletableFuture<String> future) {
        SortRequest.Builder builder = SortRequest.newBuilder();
        for (Integer i : array) {
            builder.addArray(i);
        }
        builder.setAlgorithm(algorithm);
        SortRequest request = builder.build();
        asyncStub.sort(request, new StreamObserver<SortResponse>() {
            @Override
            public void onNext(SortResponse sortResponse) {
                int n = sortResponse.getArrayCount();
                List<Integer> sortedArray = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    sortedArray.add(sortResponse.getArray(i));
                }
                future.complete(sortedArray.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                future.complete("Error : " + s.getDescription());
            }

            @Override
            public void onCompleted() {

            }
        });
        consoleService.write("Acknowledgement Received");
    }

}
