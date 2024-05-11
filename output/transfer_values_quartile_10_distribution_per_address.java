// ORM class for table 'transfer_values_quartile_10_distribution_per_address'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Apr 22 17:25:42 MSK 2024
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class transfer_values_quartile_10_distribution_per_address extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transfer_values_quartile_10_distribution_per_address.this.address = (String)value;
      }
    });
    setters.put("quartiles", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transfer_values_quartile_10_distribution_per_address.this.quartiles = (String)value;
      }
    });
    setters.put("relative_value", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transfer_values_quartile_10_distribution_per_address.this.relative_value = (Float)value;
      }
    });
  }
  public transfer_values_quartile_10_distribution_per_address() {
    init0();
  }
  private String address;
  public String get_address() {
    return address;
  }
  public void set_address(String address) {
    this.address = address;
  }
  public transfer_values_quartile_10_distribution_per_address with_address(String address) {
    this.address = address;
    return this;
  }
  private String quartiles;
  public String get_quartiles() {
    return quartiles;
  }
  public void set_quartiles(String quartiles) {
    this.quartiles = quartiles;
  }
  public transfer_values_quartile_10_distribution_per_address with_quartiles(String quartiles) {
    this.quartiles = quartiles;
    return this;
  }
  private Float relative_value;
  public Float get_relative_value() {
    return relative_value;
  }
  public void set_relative_value(Float relative_value) {
    this.relative_value = relative_value;
  }
  public transfer_values_quartile_10_distribution_per_address with_relative_value(Float relative_value) {
    this.relative_value = relative_value;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof transfer_values_quartile_10_distribution_per_address)) {
      return false;
    }
    transfer_values_quartile_10_distribution_per_address that = (transfer_values_quartile_10_distribution_per_address) o;
    boolean equal = true;
    equal = equal && (this.address == null ? that.address == null : this.address.equals(that.address));
    equal = equal && (this.quartiles == null ? that.quartiles == null : this.quartiles.equals(that.quartiles));
    equal = equal && (this.relative_value == null ? that.relative_value == null : this.relative_value.equals(that.relative_value));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof transfer_values_quartile_10_distribution_per_address)) {
      return false;
    }
    transfer_values_quartile_10_distribution_per_address that = (transfer_values_quartile_10_distribution_per_address) o;
    boolean equal = true;
    equal = equal && (this.address == null ? that.address == null : this.address.equals(that.address));
    equal = equal && (this.quartiles == null ? that.quartiles == null : this.quartiles.equals(that.quartiles));
    equal = equal && (this.relative_value == null ? that.relative_value == null : this.relative_value.equals(that.relative_value));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.address = JdbcWritableBridge.readString(1, __dbResults);
    this.quartiles = JdbcWritableBridge.readString(2, __dbResults);
    this.relative_value = JdbcWritableBridge.readFloat(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.address = JdbcWritableBridge.readString(1, __dbResults);
    this.quartiles = JdbcWritableBridge.readString(2, __dbResults);
    this.relative_value = JdbcWritableBridge.readFloat(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(quartiles, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(relative_value, 3 + __off, 7, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(quartiles, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(relative_value, 3 + __off, 7, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.address = null;
    } else {
    this.address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.quartiles = null;
    } else {
    this.quartiles = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.relative_value = null;
    } else {
    this.relative_value = Float.valueOf(__dataIn.readFloat());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, address);
    }
    if (null == this.quartiles) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, quartiles);
    }
    if (null == this.relative_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.relative_value);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, address);
    }
    if (null == this.quartiles) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, quartiles);
    }
    if (null == this.relative_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.relative_value);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(address==null?"null":address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(quartiles==null?"null":quartiles, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(relative_value==null?"null":"" + relative_value, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(address==null?"null":address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(quartiles==null?"null":quartiles, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(relative_value==null?"null":"" + relative_value, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.address = null; } else {
      this.address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.quartiles = null; } else {
      this.quartiles = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.relative_value = null; } else {
      this.relative_value = Float.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.address = null; } else {
      this.address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.quartiles = null; } else {
      this.quartiles = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.relative_value = null; } else {
      this.relative_value = Float.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    transfer_values_quartile_10_distribution_per_address o = (transfer_values_quartile_10_distribution_per_address) super.clone();
    return o;
  }

  public void clone0(transfer_values_quartile_10_distribution_per_address o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("address", this.address);
    __sqoop$field_map.put("quartiles", this.quartiles);
    __sqoop$field_map.put("relative_value", this.relative_value);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("address", this.address);
    __sqoop$field_map.put("quartiles", this.quartiles);
    __sqoop$field_map.put("relative_value", this.relative_value);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
