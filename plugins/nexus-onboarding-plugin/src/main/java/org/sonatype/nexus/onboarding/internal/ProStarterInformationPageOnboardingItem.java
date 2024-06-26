/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.onboarding.internal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.common.app.FeatureFlag;
import org.sonatype.nexus.onboarding.OnboardingItem;
import org.sonatype.nexus.onboarding.capability.OnboardingCapabilityHelper;

import static com.google.common.base.Preconditions.checkNotNull;

@Named
@Singleton
@FeatureFlag(name = "nexus.onboarding.license.enabled")
public class ProStarterInformationPageOnboardingItem
    implements OnboardingItem
{
  private final InstanceStatus instanceStatus;

  private final OnboardingCapabilityHelper onboardingCapabilityHelper;

  @Inject
  public ProStarterInformationPageOnboardingItem(
      final InstanceStatus instanceStatus, final OnboardingCapabilityHelper onboardingCapabilityHelper)
  {
    this.instanceStatus = checkNotNull(instanceStatus);
    this.onboardingCapabilityHelper = checkNotNull(onboardingCapabilityHelper);
  }

  @Override
  public String getType() {
    return "ProStarterInformationPage";
  }

  @Override
  public boolean applies() {
    return instanceStatus.isUpgraded() &&
        !onboardingCapabilityHelper.getOnboardingCapability().isProStarterInfoPageCompleted();
  }

  @Override
  public int getPriority() {
    return 0;
  }
}
