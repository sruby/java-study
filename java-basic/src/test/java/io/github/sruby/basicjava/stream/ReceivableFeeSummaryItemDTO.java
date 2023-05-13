package io.github.sruby.basicjava.stream;

public class ReceivableFeeSummaryItemDTO {
        private long id;
        private int totalReceivableFee;
        private int totalReceivableFeeTax;
        private int totalReceivableFeeWithTax;

        public ReceivableFeeSummaryItemDTO(long id, int totalReceivableFee, int totalReceivableFeeTax, int totalReceivableFeeWithTax) {
            this.id = id;
            this.totalReceivableFee = totalReceivableFee;
            this.totalReceivableFeeTax = totalReceivableFeeTax;
            this.totalReceivableFeeWithTax = totalReceivableFeeWithTax;
        }

        public long getId() {
            return id;
        }

        public int getTotalReceivableFee() {
            return totalReceivableFee;
        }

        public void setTotalReceivableFee(int totalReceivableFee) {
            this.totalReceivableFee = totalReceivableFee;
        }

        public int getTotalReceivableFeeTax() {
            return totalReceivableFeeTax;
        }

        public void setTotalReceivableFeeTax(int totalReceivableFeeTax) {
            this.totalReceivableFeeTax = totalReceivableFeeTax;
        }

        public int getTotalReceivableFeeWithTax() {
            return totalReceivableFeeWithTax;
        }

        public void setTotalReceivableFeeWithTax(int totalReceivableFeeWithTax) {
            this.totalReceivableFeeWithTax = totalReceivableFeeWithTax;
        }

        @Override
        public String toString() {
            return "ReceivableFeeSummaryItemDTO{" +
                    "id=" + id +
                    ", totalReceivableFee=" + totalReceivableFee +
                    ", totalReceivableFeeTax=" + totalReceivableFeeTax +
                    ", totalReceivableFeeWithTax=" + totalReceivableFeeWithTax +
                    '}';
        }
    }