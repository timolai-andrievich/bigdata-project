// ORM class for table 'current_market_values'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Apr 22 17:26:06 MSK 2024
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

public class current_market_values extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("nft_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        current_market_values.this.nft_address = (String)value;
      }
    });
    setters.put("token_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        current_market_values.this.token_id = (String)value;
      }
    });
    setters.put("market_value", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        current_market_values.this.market_value = (Float)value;
      }
    });
  }
  public current_market_values() {
    init0();
  }
  private String nft_address;
  public String get_nft_address() {
    return nft_address;
  }
  public void set_nft_address(String nft_address) {
    this.nft_address = nft_address;
  }
  public current_market_values with_nft_address(String nft_address) {
    this.nft_address = nft_address;
    return this;
  }
  private String token_id;
  public String get_token_id() {
    return token_id;
  }
  public void set_token_id(String token_id) {
    this.token_id = token_id;
  }
  public current_market_values with_token_id(String token_id) {
    this.token_id = token_id;
    return this;
  }
  private Float market_value;
  public Float get_market_value() {
    return market_value;
  }
  public void set_market_value(Float market_value) {
    this.market_value = market_value;
  }
  public current_market_values with_market_value(Float market_value) {
    this.market_value = market_value;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof current_market_values)) {
      return false;
    }
    current_market_values that = (current_market_values) o;
    boolean equal = true;
    equal = equal && (this.nft_address == null ? that.nft_address == null : this.nft_address.equals(that.nft_address));
    equal = equal && (this.token_id == null ? that.token_id == null : this.token_id.equals(that.token_id));
    equal = equal && (this.market_value == null ? that.market_value == null : this.market_value.equals(that.market_value));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof current_market_values)) {
      return false;
    }
    current_market_values that = (current_market_values) o;
    boolean equal = true;
    equal = equal && (this.nft_address == null ? that.nft_address == null : this.nft_address.equals(that.nft_address));
    equal = equal && (this.token_id == null ? that.token_id == null : this.token_id.equals(that.token_id));
    equal = equal && (this.market_value == null ? that.market_value == null : this.market_value.equals(that.market_value));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.nft_address = JdbcWritableBridge.readString(1, __dbResults);
    this.token_id = JdbcWritableBridge.readString(2, __dbResults);
    this.market_value = JdbcWritableBridge.readFloat(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.nft_address = JdbcWritableBridge.readString(1, __dbResults);
    this.token_id = JdbcWritableBridge.readString(2, __dbResults);
    this.market_value = JdbcWritableBridge.readFloat(3, __dbResults);
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
    JdbcWritableBridge.writeString(nft_address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(token_id, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(market_value, 3 + __off, 7, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(nft_address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(token_id, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(market_value, 3 + __off, 7, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.nft_address = null;
    } else {
    this.nft_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.token_id = null;
    } else {
    this.token_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.market_value = null;
    } else {
    this.market_value = Float.valueOf(__dataIn.readFloat());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.nft_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nft_address);
    }
    if (null == this.token_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, token_id);
    }
    if (null == this.market_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.market_value);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.nft_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nft_address);
    }
    if (null == this.token_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, token_id);
    }
    if (null == this.market_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.market_value);
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
    __sb.append(FieldFormatter.escapeAndEnclose(nft_address==null?"null":nft_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(token_id==null?"null":token_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(market_value==null?"null":"" + market_value, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(nft_address==null?"null":nft_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(token_id==null?"null":token_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(market_value==null?"null":"" + market_value, delimiters));
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
    if (__cur_str.equals("null")) { this.nft_address = null; } else {
      this.nft_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.token_id = null; } else {
      this.token_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.market_value = null; } else {
      this.market_value = Float.valueOf(__cur_str);
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
    if (__cur_str.equals("null")) { this.nft_address = null; } else {
      this.nft_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.token_id = null; } else {
      this.token_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.market_value = null; } else {
      this.market_value = Float.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    current_market_values o = (current_market_values) super.clone();
    return o;
  }

  public void clone0(current_market_values o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("nft_address", this.nft_address);
    __sqoop$field_map.put("token_id", this.token_id);
    __sqoop$field_map.put("market_value", this.market_value);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("nft_address", this.nft_address);
    __sqoop$field_map.put("token_id", this.token_id);
    __sqoop$field_map.put("market_value", this.market_value);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
