package br.com.utilities.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public abstract class UUID {

	/*
	 * private static final Random random = new Random();
	 * 
	 * public static String getUUID(String stringValue) { java.util.UUID uuid = new
	 * java.util.UUID(new Date().getTime(),Long.parseLong(stringValue.trim()));
	 * return uuid.toString(); }
	 * 
	 * public static String getUUID(Date dateValue, String stringValue) {
	 * java.util.UUID uuid = new
	 * java.util.UUID(dateValue.getTime(),Long.parseLong(stringValue.trim()));
	 * return uuid.toString(); }
	 * 
	 * public static String getUUID(Long longValue, String stringValue) {
	 * java.util.UUID uuid = new
	 * java.util.UUID(longValue,Long.parseLong(stringValue.trim())); return
	 * uuid.toString(); }
	 */

	private static Random random = new Random();

	private static long get64LeastSignificantBitsForVersion1() {
		long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
		long variant3BitFlag = 0x8000000000000000L;
		return random63BitLong + variant3BitFlag;
	}

	private static long get64MostSignificantBitsForVersion1() {
		LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
		Duration duration = Duration.between(start, LocalDateTime.now());
		long seconds = duration.getSeconds();
		long nanos = duration.getNano();
		long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
		long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
		long version = 1 << 12;
		return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
	}

	public static String getUUID_V1() {
		java.util.UUID uuid = new java.util.UUID(get64MostSignificantBitsForVersion1(),
				get64LeastSignificantBitsForVersion1());
		return uuid.toString();
	}

	/*
	 * public static String getUUID_V3(String namespace, String name) { byte[]
	 * nameSpaceBytes = bytesFromUUID(namespace); byte[] nameBytes =
	 * name.getBytes("UTF-8"); byte[] result = joinBytes(nameSpaceBytes, nameBytes);
	 * 
	 * java.util.UUID uuid = java.util.UUID.nameUUIDFromBytes(result); }
	 */

	public static String getUUID_V4() {
		java.util.UUID uuid = java.util.UUID.randomUUID();
		return uuid.toString();
	}

}
