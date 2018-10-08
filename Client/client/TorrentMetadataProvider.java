package client;

import bcodec.InvalidBEncodingException;
import common.TorrentMetadata;

import java.io.IOException;

public interface TorrentMetadataProvider {

  /**
   * load and return new {@link TorrentMetadata} instance from any source
   *
   * @return new torrent metadata instance
   * @throws IOException               if any IO error occurs
   * @throws InvalidBEncodingException if specified source has invalid BEP format or missed required fields
   */
  TorrentMetadata getTorrentMetadata() throws IOException;

}