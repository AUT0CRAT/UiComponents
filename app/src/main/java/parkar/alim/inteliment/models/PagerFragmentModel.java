package parkar.alim.inteliment.models;

public class PagerFragmentModel {
    private int id;
    private String fragmentName;

    public PagerFragmentModel(int id) {
        this.id = id;
    }

    public PagerFragmentModel(int id, String fragmentName) {
        this.id = id;
        this.fragmentName = fragmentName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public int getId() {
        return id;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PagerFragmentModel that = (PagerFragmentModel) o;

        return id == that.id;
    }

    @Override public int hashCode() {
        return id;
    }
}