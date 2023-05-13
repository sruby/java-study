package io.github.sruby.basicjava.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream merge test
 *
 * @date 5/12/2023 7:49 PM
 */
public class StreamMergeTest {

    @Test
    public void testFullJoin1() {
        List<ReceivableFeeSummaryItemDTO> list1 = new ArrayList<>();
        list1.add(new ReceivableFeeSummaryItemDTO(1, 100, 50, 150));
        list1.add(new ReceivableFeeSummaryItemDTO(2, 200, 100, 300));
        list1.add(new ReceivableFeeSummaryItemDTO(3, 300, 150, 450));

        List<ReceivableFeeSummaryItemDTO> list2 = new ArrayList<>();
        list2.add(new ReceivableFeeSummaryItemDTO(1, 1000, 500, 1500));
        list2.add(new ReceivableFeeSummaryItemDTO(2, 2000, 1000, 3000));
        list2.add(new ReceivableFeeSummaryItemDTO(4, 400, 200, 600));

        Map<Long, ReceivableFeeSummaryItemDTO> mergedMap = new HashMap<>();

        for (ReceivableFeeSummaryItemDTO dto : list1) {
            mergedMap.put(dto.getId(), dto);
        }

        for (ReceivableFeeSummaryItemDTO dto : list2) {
            long key = dto.getId();
            ReceivableFeeSummaryItemDTO value = mergedMap.get(key);
            if (value == null) {
                // If the key doesn't exist in the merged map, add it
                mergedMap.put(key, dto);
            } else {
                // If the key does exist, add the values together
                value.setTotalReceivableFee(value.getTotalReceivableFee() + dto.getTotalReceivableFee());
                value.setTotalReceivableFeeTax(value.getTotalReceivableFeeTax() + dto.getTotalReceivableFeeTax());
                value.setTotalReceivableFeeWithTax(value.getTotalReceivableFeeWithTax() + dto.getTotalReceivableFeeWithTax());
            }
        }

        List<ReceivableFeeSummaryItemDTO> result = new ArrayList<>(mergedMap.values());

        System.out.println("Merged List: " + result);
    }

    @Test
    public void testFullJoin2() {
        List<ReceivableFeeSummaryItemDTO> list1 = new ArrayList<>();
        list1.add(new ReceivableFeeSummaryItemDTO(1, 100, 50, 150));
        list1.add(new ReceivableFeeSummaryItemDTO(2, 200, 100, 300));
        list1.add(new ReceivableFeeSummaryItemDTO(3, 300, 150, 450));
        list1.add(new ReceivableFeeSummaryItemDTO(6, 610, 1150, 4150));

        List<ReceivableFeeSummaryItemDTO> list2 = new ArrayList<>();
        list2.add(new ReceivableFeeSummaryItemDTO(1, 1000, 500, 1500));
        list2.add(new ReceivableFeeSummaryItemDTO(2, 2000, 1000, 3000));
        list2.add(new ReceivableFeeSummaryItemDTO(4, 400, 200, 600));

        Map<Long, ReceivableFeeSummaryItemDTO> mergedMap = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toMap(
                        ReceivableFeeSummaryItemDTO::getId,
                        Function.identity(),
                        (v1, v2) -> {
                            // Combine values for matching keys
                            v1.setTotalReceivableFee(v1.getTotalReceivableFee() + v2.getTotalReceivableFee());
                            v1.setTotalReceivableFeeTax(v1.getTotalReceivableFeeTax() + v2.getTotalReceivableFeeTax());
                            v1.setTotalReceivableFeeWithTax(v1.getTotalReceivableFeeWithTax() + v2.getTotalReceivableFeeWithTax());
                            return v1;
                        }));

        List<ReceivableFeeSummaryItemDTO> result = new ArrayList<>(mergedMap.values());
        System.out.println("Merged List: " + result);

    }



}
