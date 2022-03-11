public class Product
{
    private String m_sID;
    private String m_sName;
    private String m_sDescription;
    private double m_fCost;

    Product()
    {
        m_sID = "";
        m_sName = "";
        m_sDescription = "";
        m_fCost = 0.0f;
    }

    Product(String ID, String Name, String Description, double Cost)
    {
        m_sID = ID;
        m_sName = Name;
        m_sDescription = Description;
        m_fCost = Cost;
    }

    public String getM_sID() {
        return m_sID;
    }

    public String getM_sName() {
        return m_sName;
    }

    public String getM_sDescription() {
        return m_sDescription;
    }

    public double getM_fCost() {
        return m_fCost;
    }

    public void setM_sID(String m_sID) {
        this.m_sID = m_sID;
    }

    public void setM_sName(String m_sName) {
        this.m_sName = m_sName;
    }

    public void setM_sDescription(String m_sDescription) {
        this.m_sDescription = m_sDescription;
    }

    public void setM_fCost(double m_fCost) {
        this.m_fCost = m_fCost;
    }

    public String toCSVDataRecord()
    {
        String CSVDataRecord = "";

        CSVDataRecord = String.format("%s, %s, %s, %.1f", m_sID, m_sName, m_sDescription, m_fCost);

        return CSVDataRecord;
    }
}
