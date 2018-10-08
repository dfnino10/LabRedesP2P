/*
 * Copyright 2000-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package client.announce;

import common.Peer;

import java.net.URI;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.List;

public class TrackerClientFactoryImpl implements TrackerClientFactory {

	@Override
	public TrackerClient createTrackerClient(List<Peer> peers, URI tracker) throws UnknownHostException, UnknownServiceException {
		String scheme = tracker.getScheme();

		if ("http".equals(scheme) || "https".equals(scheme)) {
			return new HTTPTrackerClient(peers, tracker);
		} else if ("udp".equals(scheme)) {
			return new UDPTrackerClient(peers, tracker);
		}

		throw new UnknownServiceException(
				"Unsupported announce scheme: " + scheme + "!");
	}
}