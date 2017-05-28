package parkar.alim.inteliment.models;

public class DashboardFragmentModel {
    private int id;
    private String tabTitle;
    private String fragmentName;

    public DashboardFragmentModel(int id) {
        this.id = id;
    }

    public DashboardFragmentModel(int id, String tabTitle, String fragmentName) {
        this.id = id;
        this.tabTitle = tabTitle;
        this.fragmentName = fragmentName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public int getId() {
        return id;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashboardFragmentModel that = (DashboardFragmentModel) o;

        return id == that.id;
    }

    @Override public int hashCode() {
        return id;
    }
}