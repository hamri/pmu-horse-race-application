package com.pmu.race.kafka;

import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class RaceAvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {
    @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Serializer.super.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String topicName, T event) {
    try {
      byte[] result = null;

      if (event != null) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder =
          EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);

        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(event.getSchema());
        datumWriter.write(event, binaryEncoder);

        binaryEncoder.flush();
        byteArrayOutputStream.close();

        result = byteArrayOutputStream.toByteArray();
      }
      return result;
    } catch (IOException ex) {
      throw new SerializationException(ex);
    }
  }

  @Override
  public byte[] serialize(String topic, Headers headers, T data) {
    return Serializer.super.serialize(topic, headers, data);
  }

  @Override
  public void close() {
    Serializer.super.close();
  }
}
