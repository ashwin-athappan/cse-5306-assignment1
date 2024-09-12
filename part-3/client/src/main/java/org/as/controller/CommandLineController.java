package org.as.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.as.proto.Algorithm;
import org.as.service.ClientService;
import org.as.service.ConsoleService;
import org.springframework.shell.component.SingleItemSelector;
import org.springframework.shell.component.support.SelectorItem;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@ShellComponent
@AllArgsConstructor
public class CommandLineController extends AbstractShellComponent {

    private final ClientService clientService;
    private final ConsoleService consoleService;

    @ShellMethod(key = "/help")
    public String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to SpringBoot AddSort CLI\n");
        builder.append("Usage:\n");
        builder.append("\t/add <i> <j> - this command adds the two numbers you give as input\n");
        builder.append("\t/sort <[array]> - this command allows you to sort an array.\n\t\t\tThe input should be given like [1,2,3,4,5]\n");
        builder.append("\t/addAsync <i> <j> - this command adds the two numbers you give as input asynchronously\n");
        builder.append("\t/sortAsync <[array]> - this command allows you to sort an array asynchronously.\n\t\t\tThe input should be given like [1,2,3,4,5]\n");
        return builder.toString();
    }

    @ShellMethod(key = "/add", value = "Adds two numbers synchronously")
    public String add(@ShellOption(value = "", help = "") String i, @ShellOption(value = " ", help = "") String j) {
        log.info("Adding two numbers: {} + {}", i, j);
        consoleService.write("waiting for response from server");
        int sum = clientService.add(Integer.parseInt(i), Integer.parseInt(j));
        return "> " + i + " + " + j + " = " + sum;
    }

    @ShellMethod(key = "/addAsync", value = "Adds two numbers asynchronously")
    public String addAsync(@ShellOption(value = "", help = "") String i, @ShellOption(value = " ", help = "") String j) {
        CompletableFuture<String> future = new CompletableFuture<>();
        String result = "";
        try {
            clientService.add(Integer.parseInt(i), Integer.parseInt(j), future);
            consoleService.write("waiting for response from server");
            String res = future.get();
            if (res.contains("Error")) {
                return res;
            }
            result = "> " + i + " + " + j + " = " + res;
        } catch (InterruptedException | ExecutionException e) {
            return "Error : " + e.getMessage();
        }
        return result;
    }

    private String selectableList() {
        // Code to print the selectable list of Algorithms to the shell
        List<SelectorItem<String>> algorithms = new ArrayList<>();
        for (Algorithm algorithm: Algorithm.values()) {
            if (!algorithm.name().equals("UNRECOGNIZED")) {
                algorithms.add(SelectorItem.of(algorithm.name(), algorithm.name()));
            }
        }
        SingleItemSelector<String, SelectorItem<String>> selector = new SingleItemSelector<>(
                getTerminal(),
                algorithms,
                "Choose an Algorithm",
                null);
        selector.setResourceLoader(getResourceLoader());
        selector.setTemplateExecutor(getTemplateExecutor());
        SingleItemSelector.SingleItemSelectorContext<String, SelectorItem<String>> context =
                selector.run(SingleItemSelector.SingleItemSelectorContext.empty());

        String result = context.getResultItem().flatMap(r -> Optional.ofNullable(r.getItem())).get();
        consoleService.write("Algorithm : " + result);
        return result;
    }

    @ShellMethod(key = "/sort", value = "Sort an array of integers with algorithm of choice synchronously")
    public String sort(@ShellOption(value = "", help = "The input should be given like [1,2,3,4,5]") String inputArray) {
        log.info("Sorting: {}", inputArray);

        List<Integer> array = new ArrayList<>();
        inputArray = inputArray.trim();
        inputArray = inputArray.replaceAll("\\[", "");
        inputArray = inputArray.replaceAll("]", "");
        String[] nums = inputArray.split(",");

        try {
            for (String num : nums) {
                array.add(Integer.parseInt(num));
            }
        } catch (NumberFormatException e) {
            return "Error : " + e.getMessage();
        }

        String result = selectableList();
        Algorithm chosenAlgorithm = Algorithm.valueOf(result);
        consoleService.write("waiting for response from server");
        // Call to client service
        List<Integer> sortedArray = clientService.sort(chosenAlgorithm, array);
        return "Array Sorted using " + chosenAlgorithm.name() + ":" + sortedArray;
    }

    @ShellMethod(key = "/sortAsync", value = "Sort an array of integers with algorithm of choice asynchronously")
    public String sortAsync(@ShellOption(value = "", help = "The input should be given like [1,2,3,4,5]") String inputArray) {
        CompletableFuture<String> future = new CompletableFuture<>();
        Algorithm chosenAlgorithm = Algorithm.UNRECOGNIZED;
        String sortedArray = "";
        try {
            List<Integer> array = new ArrayList<>();
            inputArray = inputArray.trim();
            inputArray = inputArray.replaceAll("\\[", "");
            inputArray = inputArray.replaceAll("]", "");
            String[] nums = inputArray.split(",");

            for (String num : nums) {
                array.add(Integer.parseInt(num));
            }

            String result = selectableList();
            chosenAlgorithm = Algorithm.valueOf(result);
            consoleService.write("waiting for response from server");
            // Call to client service
            clientService.sort(chosenAlgorithm, array, future);
            sortedArray = future.get();
        } catch (InterruptedException | ExecutionException | NumberFormatException e) {
            return "Error : " + e.getMessage();
        }

        return "Array Sorted using " + chosenAlgorithm.name() + ": " + sortedArray;
    }
}
