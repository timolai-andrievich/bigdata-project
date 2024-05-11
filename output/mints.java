// ORM class for table 'mints'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Apr 22 17:28:22 MSK 2024
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

public class mints extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("event_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.event_id = (String)value;
      }
    });
    setters.put("transaction_hash", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.transaction_hash = (String)value;
      }
    });
    setters.put("block_number", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.block_number = (Integer)value;
      }
    });
    setters.put("nft_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.nft_address = (String)value;
      }
    });
    setters.put("token_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.token_id = (String)value;
      }
    });
    setters.put("from_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.from_address = (String)value;
      }
    });
    setters.put("to_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.to_address = (String)value;
      }
    });
    setters.put("transaction_value", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.transaction_value = (Float)value;
      }
    });
    setters.put("timestamp", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        mints.this.timestamp = (Integer)value;
      }
    });
  }
  public mints() {
    init0();
  }
  private String event_id;
  public String get_event_id() {
    return event_id;
  }
  public void set_event_id(String event_id) {
    this.event_id = event_id;
  }
  public mints with_event_id(String event_id) {
    this.event_id = event_id;
    return this;
  }
  private String transaction_hash;
  public String get_transaction_hash() {
    return transaction_hash;
  }
  public void set_transaction_hash(String transaction_hash) {
    this.transaction_hash = transaction_hash;
  }
  public mints with_transaction_hash(String transaction_hash) {
    this.transaction_hash = transaction_hash;
    return this;
  }
  private Integer block_number;
  public Integer get_block_number() {
    return block_number;
  }
  public void set_block_number(Integer block_number) {
    this.block_number = block_number;
  }
  public mints with_block_number(Integer block_number) {
    this.block_number = block_number;
    return this;
  }
  private String nft_address;
  public String get_nft_address() {
    return nft_address;
  }
  public void set_nft_address(String nft_address) {
    this.nft_address = nft_address;
  }
  public mints with_nft_address(String nft_address) {
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
  public mints with_token_id(String token_id) {
    this.token_id = token_id;
    return this;
  }
  private String from_address;
  public String get_from_address() {
    return from_address;
  }
  public void set_from_address(String from_address) {
    this.from_address = from_address;
  }
  public mints with_from_address(String from_address) {
    this.from_address = from_address;
    return this;
  }
  private String to_address;
  public String get_to_address() {
    return to_address;
  }
  public void set_to_address(String to_address) {
    this.to_address = to_address;
  }
  public mints with_to_address(String to_address) {
    this.to_address = to_address;
    return this;
  }
  private Float transaction_value;
  public Float get_transaction_value() {
    return transaction_value;
  }
  public void set_transaction_value(Float transaction_value) {
    this.transaction_value = transaction_value;
  }
  public mints with_transaction_value(Float transaction_value) {
    this.transaction_value = transaction_value;
    return this;
  }
  private Integer timestamp;
  public Integer get_timestamp() {
    return timestamp;
  }
  public void set_timestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }
  public mints with_timestamp(Integer timestamp) {
    this.timestamp = timestamp;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof mints)) {
      return false;
    }
    mints that = (mints) o;
    boolean equal = true;
    equal = equal && (this.event_id == null ? that.event_id == null : this.event_id.equals(that.event_id));
    equal = equal && (this.transaction_hash == null ? that.transaction_hash == null : this.transaction_hash.equals(that.transaction_hash));
    equal = equal && (this.block_number == null ? that.block_number == null : this.block_number.equals(that.block_number));
    equal = equal && (this.nft_address == null ? that.nft_address == null : this.nft_address.equals(that.nft_address));
    equal = equal && (this.token_id == null ? that.token_id == null : this.token_id.equals(that.token_id));
    equal = equal && (this.from_address == null ? that.from_address == null : this.from_address.equals(that.from_address));
    equal = equal && (this.to_address == null ? that.to_address == null : this.to_address.equals(that.to_address));
    equal = equal && (this.transaction_value == null ? that.transaction_value == null : this.transaction_value.equals(that.transaction_value));
    equal = equal && (this.timestamp == null ? that.timestamp == null : this.timestamp.equals(that.timestamp));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof mints)) {
      return false;
    }
    mints that = (mints) o;
    boolean equal = true;
    equal = equal && (this.event_id == null ? that.event_id == null : this.event_id.equals(that.event_id));
    equal = equal && (this.transaction_hash == null ? that.transaction_hash == null : this.transaction_hash.equals(that.transaction_hash));
    equal = equal && (this.block_number == null ? that.block_number == null : this.block_number.equals(that.block_number));
    equal = equal && (this.nft_address == null ? that.nft_address == null : this.nft_address.equals(that.nft_address));
    equal = equal && (this.token_id == null ? that.token_id == null : this.token_id.equals(that.token_id));
    equal = equal && (this.from_address == null ? that.from_address == null : this.from_address.equals(that.from_address));
    equal = equal && (this.to_address == null ? that.to_address == null : this.to_address.equals(that.to_address));
    equal = equal && (this.transaction_value == null ? that.transaction_value == null : this.transaction_value.equals(that.transaction_value));
    equal = equal && (this.timestamp == null ? that.timestamp == null : this.timestamp.equals(that.timestamp));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.event_id = JdbcWritableBridge.readString(1, __dbResults);
    this.transaction_hash = JdbcWritableBridge.readString(2, __dbResults);
    this.block_number = JdbcWritableBridge.readInteger(3, __dbResults);
    this.nft_address = JdbcWritableBridge.readString(4, __dbResults);
    this.token_id = JdbcWritableBridge.readString(5, __dbResults);
    this.from_address = JdbcWritableBridge.readString(6, __dbResults);
    this.to_address = JdbcWritableBridge.readString(7, __dbResults);
    this.transaction_value = JdbcWritableBridge.readFloat(8, __dbResults);
    this.timestamp = JdbcWritableBridge.readInteger(9, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.event_id = JdbcWritableBridge.readString(1, __dbResults);
    this.transaction_hash = JdbcWritableBridge.readString(2, __dbResults);
    this.block_number = JdbcWritableBridge.readInteger(3, __dbResults);
    this.nft_address = JdbcWritableBridge.readString(4, __dbResults);
    this.token_id = JdbcWritableBridge.readString(5, __dbResults);
    this.from_address = JdbcWritableBridge.readString(6, __dbResults);
    this.to_address = JdbcWritableBridge.readString(7, __dbResults);
    this.transaction_value = JdbcWritableBridge.readFloat(8, __dbResults);
    this.timestamp = JdbcWritableBridge.readInteger(9, __dbResults);
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
    JdbcWritableBridge.writeString(event_id, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(transaction_hash, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(block_number, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nft_address, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(token_id, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(from_address, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(to_address, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(transaction_value, 8 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(timestamp, 9 + __off, 4, __dbStmt);
    return 9;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(event_id, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(transaction_hash, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(block_number, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nft_address, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(token_id, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(from_address, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(to_address, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(transaction_value, 8 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(timestamp, 9 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.event_id = null;
    } else {
    this.event_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.transaction_hash = null;
    } else {
    this.transaction_hash = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.block_number = null;
    } else {
    this.block_number = Integer.valueOf(__dataIn.readInt());
    }
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
        this.from_address = null;
    } else {
    this.from_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.to_address = null;
    } else {
    this.to_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.transaction_value = null;
    } else {
    this.transaction_value = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.timestamp = null;
    } else {
    this.timestamp = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.event_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, event_id);
    }
    if (null == this.transaction_hash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, transaction_hash);
    }
    if (null == this.block_number) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.block_number);
    }
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
    if (null == this.from_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, from_address);
    }
    if (null == this.to_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, to_address);
    }
    if (null == this.transaction_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.transaction_value);
    }
    if (null == this.timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.timestamp);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.event_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, event_id);
    }
    if (null == this.transaction_hash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, transaction_hash);
    }
    if (null == this.block_number) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.block_number);
    }
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
    if (null == this.from_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, from_address);
    }
    if (null == this.to_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, to_address);
    }
    if (null == this.transaction_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.transaction_value);
    }
    if (null == this.timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.timestamp);
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
    __sb.append(FieldFormatter.escapeAndEnclose(event_id==null?"null":event_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transaction_hash==null?"null":transaction_hash, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(block_number==null?"null":"" + block_number, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nft_address==null?"null":nft_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(token_id==null?"null":token_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(from_address==null?"null":from_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(to_address==null?"null":to_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transaction_value==null?"null":"" + transaction_value, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(timestamp==null?"null":"" + timestamp, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(event_id==null?"null":event_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transaction_hash==null?"null":transaction_hash, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(block_number==null?"null":"" + block_number, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nft_address==null?"null":nft_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(token_id==null?"null":token_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(from_address==null?"null":from_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(to_address==null?"null":to_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transaction_value==null?"null":"" + transaction_value, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(timestamp==null?"null":"" + timestamp, delimiters));
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
    if (__cur_str.equals("null")) { this.event_id = null; } else {
      this.event_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.transaction_hash = null; } else {
      this.transaction_hash = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.block_number = null; } else {
      this.block_number = Integer.valueOf(__cur_str);
    }

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
    if (__cur_str.equals("null")) { this.from_address = null; } else {
      this.from_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.to_address = null; } else {
      this.to_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transaction_value = null; } else {
      this.transaction_value = Float.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.timestamp = null; } else {
      this.timestamp = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null")) { this.event_id = null; } else {
      this.event_id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.transaction_hash = null; } else {
      this.transaction_hash = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.block_number = null; } else {
      this.block_number = Integer.valueOf(__cur_str);
    }

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
    if (__cur_str.equals("null")) { this.from_address = null; } else {
      this.from_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.to_address = null; } else {
      this.to_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transaction_value = null; } else {
      this.transaction_value = Float.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.timestamp = null; } else {
      this.timestamp = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    mints o = (mints) super.clone();
    return o;
  }

  public void clone0(mints o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("event_id", this.event_id);
    __sqoop$field_map.put("transaction_hash", this.transaction_hash);
    __sqoop$field_map.put("block_number", this.block_number);
    __sqoop$field_map.put("nft_address", this.nft_address);
    __sqoop$field_map.put("token_id", this.token_id);
    __sqoop$field_map.put("from_address", this.from_address);
    __sqoop$field_map.put("to_address", this.to_address);
    __sqoop$field_map.put("transaction_value", this.transaction_value);
    __sqoop$field_map.put("timestamp", this.timestamp);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("event_id", this.event_id);
    __sqoop$field_map.put("transaction_hash", this.transaction_hash);
    __sqoop$field_map.put("block_number", this.block_number);
    __sqoop$field_map.put("nft_address", this.nft_address);
    __sqoop$field_map.put("token_id", this.token_id);
    __sqoop$field_map.put("from_address", this.from_address);
    __sqoop$field_map.put("to_address", this.to_address);
    __sqoop$field_map.put("transaction_value", this.transaction_value);
    __sqoop$field_map.put("timestamp", this.timestamp);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
