package com.gridnine.testing;

public class FilterManager {
    private Filter filter;

    public FilterManager(Filter filter) {
        this.filter = filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public boolean filter(Flight flight) {
        return filter.check(flight);
    }
}
